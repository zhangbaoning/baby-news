<template>
    <div class="fillcontain">
        <head-top></head-top>
        <div class="table_container">
            <el-table
                :data="tableData"
                style="width: 100%">
                <el-table-column prop="id" v-if="false"/>
                <el-table-column
                    label="分类名称"
                    prop="name">
                </el-table-column>
                <el-table-column
                    label="分类介绍"
                    prop="description">
                </el-table-column>
                <!--<el-table-column-->
                <!--label="评分"-->
                <!--prop="rating">-->
                <!--</el-table-column>-->
                <el-table-column label="操作" width="160">
                    <template scope="scope">
                        <el-button
                            size="small"
                            @click="handleEdit(scope.row)">编辑
                        </el-button>
                        <el-button
                            size="small"
                            type="danger"
                            @click="handleDelete(scope.$index, scope.row)">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="Pagination">
                <el-pagination
                    :current-page="currentPage"
                    :page-size="20"
                    layout="total, prev, pager, next"
                    :total="count">
                </el-pagination>
            </div>
            <el-dialog title="修改食品信息" v-model="dialogFormVisible">
                <el-form :model="selectTable">
                    <el-form-item label="食品名称" label-width="100px">
                        <el-input v-model="selectTable.name" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="食品介绍" label-width="100px">
                        <el-input v-model="selectTable.description"></el-input>
                    </el-form-item>
                    <el-form-item label="食品分类" label-width="100px">
                        <el-select v-model="selectIndex" :placeholder="selectMenu.label">
                            <el-option
                                v-for="item in menuOptions"
                                :key="item.value"
                                :label="item.label"
                                :value="item.index">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="updateFood">确 定</el-button>
                </div>
            </el-dialog>


            <el-dialog title="添加规格" v-model="specsFormVisible">
                <el-form :rules="specsFormrules" :model="specsForm">
                    <el-form-item label="规格" label-width="100px" prop="specs">
                        <el-input v-model="specsForm.specs" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="包装费" label-width="100px">
                        <el-input-number v-model="specsForm.packing_fee" :min="0" :max="100"></el-input-number>
                    </el-form-item>
                    <el-form-item label="价格" label-width="100px">
                        <el-input-number v-model="specsForm.price" :min="0" :max="10000"></el-input-number>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="specsFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="">确 定</el-button>
                </div>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    import headTop from '../components/headTop'
    import {baseImgPath, baseUrl} from '@/config/env'
    import {
        deleteFood,
        getFoods,
        getFoodsCount,
        getMenu,
        getMenuById,
        getResturantDetail,
        updateFood
    } from '@/api/getData'

    export default {
        data() {
            return {
                baseUrl,
                baseImgPath,
                restaurant_id: null,
                city: {},
                offset: 0,
                limit: 20,
                count: 0,
                tableData: [],
                currentPage: 1,
                selectTable: {},
                dialogFormVisible: false,
                menuOptions: [],
                selectMenu: {},
                selectIndex: null,
                specsForm: {
                    specs: '',
                    packing_fee: 0,
                    price: 20,
                },
                specsFormrules: {
                    specs: [
                        {required: true, message: '请输入规格', trigger: 'blur'},
                    ],
                },
                specsFormVisible: false,
                expendRow: [],
            }
        },
        created() {
            this.initData();
        },
        computed: {
            specs: function () {
                let specs = [];
                if (this.selectTable.specfoods) {
                    this.selectTable.specfoods.forEach(item => {
                        specs.push({
                            specs: item.specs_name,
                            packing_fee: item.packing_fee,
                            price: item.price,
                        })
                    })
                }
                return specs
            }
        },
        components: {
            headTop,
        },
        methods: {
            handleDelete(index, row) {
                console.log('删除');
                console.log(row.id);
                // var para = {
                //     id: row.id
                // }
                this.$ajax.get('apis/category/delete', {params: {id: row.id}});
            },
            initData() {
                var _this = this;
                this.$ajax.post('apis/category/getAll').then(function (res) {
                    _this.tableData = res.data;
                });
            },
            async getMenu() {
                this.menuOptions = [];
                try {
                    const menu = await getMenu({restaurant_id: this.selectTable.restaurant_id, allMenu: true});
                    menu.forEach((item, index) => {
                        this.menuOptions.push({
                            label: item.name,
                            value: item.id,
                            index,
                        })
                    })
                } catch (err) {
                    console.log('获取食品种类失败', err);
                }
            },
            async getFoods() {
                const Foods = await getFoods({
                    offset: this.offset,
                    limit: this.limit,
                    restaurant_id: this.restaurant_id
                });
                this.tableData = [];
                Foods.forEach((item, index) => {
                    const tableData = {};
                    tableData.name = item.name;
                    tableData.item_id = item.item_id;
                    tableData.description = item.description;
                    tableData.rating = item.rating;
                    tableData.month_sales = item.month_sales;
                    tableData.restaurant_id = item.restaurant_id;
                    tableData.category_id = item.category_id;
                    tableData.image_path = item.image_path;
                    tableData.specfoods = item.specfoods;
                    tableData.index = index;
                    this.tableData.push(tableData);
                })
            }

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
