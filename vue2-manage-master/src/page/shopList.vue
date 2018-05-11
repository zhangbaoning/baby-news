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
                    prop="author">
                </el-table-column>
                <el-table-column
                    label="分类"
                    prop="category">
                </el-table-column>
                <el-table-column
                    label="创建时间"
                    prop="datePublish"
                    :formatter="dateFormat">
                </el-table-column>
                <el-table-column
                    label="修改时间"
                    prop="dateModified"
                    :formatter="dateFormat">
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
    import moment from 'moment'

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
            //时间格式化
            dateFormat: function (row, column) {
                var date = row[column.property];
                if (date == undefined) {
                    return "";
                }
                return moment(date).format("YYYY-MM-DD HH:mm:ss");
            },
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
            },
            handleEdit(index, row) {
                this.$router.push({path: 'vueEdit', query: {article_id: row.id}});

            },
            async handleDelete(index, row) {
                try {
                    console.log(row.id);
                    this.$ajax.get('apis/article/delete', {
                        params: {
                            articleId: row.id
                        }
                    });
                    setTimeout(() => {
                        this.getArticleList();
                    }, 500);
                    this.tableData.splice(index, 1);
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
                this.$ajax.get('apis/article/getAll').then(function (res) {
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
