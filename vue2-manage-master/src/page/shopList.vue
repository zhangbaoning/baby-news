<template>
    <div class="fillcontain">
        <head-top></head-top>
        <div class="table_container">
            <el-table
                :data="tableData"
                style="width: 100%">
                <el-form-item prop="id" v-if="false"/>
                <el-form-item
                    label="分类"
                    prop="category">
                </el-form-item>
                <el-table-column
                    label="文章题目"
                    prop="title">
                </el-table-column>
                <el-table-column
                    label="文章介绍"
                    prop="descr">
                </el-table-column>
                <el-table-column
                    label="文章作者"
                    prop="user">
                </el-table-column>
                <el-table-column
                    label="创建时间"
                    prop="datePublish">
                </el-table-column>
                <el-table-column
                    label="修改时间"
                    prop="dateOfModified">
                </el-table-column>
                <el-table-column label="操作" width="200">
                    <template scope="scope">
                        <el-button
                            size="mini"
                            @click="handleEdit(scope.$index, scope.row)">编辑
                        </el-button>
                        <el-button
                            size="mini"
                            type="danger"
                            @click="handleDelete(scope.$index, scope.row)">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="Pagination">
                <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="currentPage"
                    :page-size="20"
                    layout="total, prev, pager, next"
                    :total="count">
                </el-pagination>
            </div>
        </div>
    </div>
</template>

<script>
    import headTop from '../components/headTop'
    import {baseImgPath, baseUrl} from '@/config/env'
    import {
        cityGuess,
        deleteResturant,
        foodCategory,
        getResturants,
        getResturantsCount,
        searchplace,
        updateResturant
    } from '@/api/getData'

    export default {
        data() {
            return {
                baseUrl,
                baseImgPath,
                city: {},
                offset: 0,
                limit: 20,
                count: 0,
                tableData: [],
                currentPage: 1,
                selectTable: {},
                dialogFormVisible: false,
                categoryOptions: [],
                selectedCategory: [],
                address: {},
            }
        },
        components: {
            headTop,
        },
        methods: {
            async getCategory() {
                try {
                    const categories = await foodCategory();
                    categories.forEach(item => {
                        if (item.sub_categories.length) {
                            const addnew = {
                                value: item.name,
                                label: item.name,
                                children: []
                            }
                            item.sub_categories.forEach((subitem, index) => {
                                if (index == 0) {
                                    return
                                }
                                addnew.children.push({
                                    value: subitem.name,
                                    label: subitem.name,
                                })
                            })
                            this.categoryOptions.push(addnew)
                        }
                    })
                } catch (err) {
                    console.log('获取商铺种类失败', err);
                }
            },
            async getResturants() {
                const {latitude, longitude} = this.city;
                const restaurants = await getResturants({latitude, longitude, offset: this.offset, limit: this.limit});
                this.tableData = [];
                restaurants.forEach(item => {
                    const tableData = {};
                    tableData.name = item.name;
                    tableData.address = item.address;
                    tableData.description = item.description;
                    tableData.id = item.id;
                    tableData.phone = item.phone;
                    tableData.rating = item.rating;
                    tableData.recent_order_num = item.recent_order_num;
                    tableData.category = item.category;
                    tableData.image_path = item.image_path;
                    this.tableData.push(tableData);
                })
            },
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
            },
            handleCurrentChange(val) {
                this.currentPage = val;
                this.offset = (val - 1) * this.limit;
                this.getResturants()
            },
            handleEdit(index, row) {
                this.$router.push({path: 'vueEdit', query: {article_id: row.id}});

            },
            async handleDelete(index, row) {
                try {
                    console.log(row.id);
                    this.$ajax.post('apis/article/delete', row.id);
                    this.getArticleList();
                    // if (res.status == 1) {
                    //     this.$message({
                    //         type: 'success',
                    //         message: '删除文章成功'
                    //     });
                    //     this.tableData.splice(index, 1);
                    // } else {
                    //     throw new Error(res.message)
                    // }
                } catch (err) {
                    this.$message({
                        type: 'error',
                        message: err.message
                    });
                    console.log('删除店铺失败')
                }
            },
            getArticleList() {
                let para = {
                    page: 1,
                    size: 10
                };
                var _this = this;
                this.$ajax.post('apis/article/getAll?page=1&size=10').then(function (res) {
                    // this.tableData = res.data;
                    _this.tableData = res.data;
                    _this.tableData.category = res.data.category;
                    console.log(res.data);
                });

            }
        },
        mounted: function () {
            this.getArticleList();
        }
    }
</script>

<style lang="less">
    @import '../style/mixin';

    .demo-table-expand {
        font-size: 0;
    }

    .demo-table-expand label {
        width: 90px;
        color: #99a9bf;
    }

    .demo-table-expand .el-form-item {
        margin-right: 0;
        margin-bottom: 0;
        width: 50%;
    }

    .table_container {
        padding: 20px;
    }

    .Pagination {
        display: flex;
        justify-content: flex-start;
        margin-top: 8px;
    }

    .avatar-uploader .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }

    .avatar-uploader .el-upload:hover {
        border-color: #20a0ff;
    }

    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 120px;
        height: 120px;
        line-height: 120px;
        text-align: center;
    }

    .avatar {
        width: 120px;
        height: 120px;
        display: block;
    }
</style>
