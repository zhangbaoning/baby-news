<template>
    <div class="fillcontain">
        <head-top></head-top>
        <div class="table_container">
            <el-button
                type="primary" @click="addCategory()">新增
            </el-button>
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
            <el-dialog title="修改分类信息" v-model="dialogFormVisible">
                <el-form :model="selectTable">
                    <el-form-item label="分类id" label-width="100px">
                        <el-input v-model="selectTable.id" auto-complete="off" :disabled="true"></el-input>
                    </el-form-item>
                    <el-form-item label="分类名称" label-width="100px">
                        <el-input v-model="selectTable.name" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="分类介绍" label-width="100px">
                        <el-input v-model="selectTable.description" auto-complete="off"></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="updateCategory">确 定</el-button>
                </div>
            </el-dialog>


            <el-dialog title="添加分类" v-model="addFormVisible">
                <el-form :model="selectTable">
                    <el-form-item label="分类名称" label-width="100px">
                        <el-input v-model="selectTable.name" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="分类介绍" label-width="100px">
                        <el-input v-model="selectTable.description"></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="specsFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="addCategoryConfirm()">确 定</el-button>
                </div>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    import headTop from '../components/headTop'
    import {baseImgPath, baseUrl} from '@/config/env'

    export default {
        data() {
            return {
                addFormVisible: false, // 新增页面显示
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
            // 添加分类提交
            addCategoryConfirm() {
                console.log('222222222222221111222');
                console.log(this.selectTable);
                this.$ajax.post('apis/category/add', this.selectTable);
                this.addFormVisible = false;
                setTimeout(() => {
                    this.initData()
                }, 500);
            },
            // 添加分类页面
            addCategory() {
                this.addFormVisible = true;
                this.$ajax.post('apis/category/update', this.selectTable);
            },
            // 更新分类
            updateCategory() {
                console.log(this.selectTable);
                var para = {
                    id: this.selectTable.id,
                    name: this.selectTable.name,
                    description: this.selectTable.description
                };
                this.$ajax.post('apis/category/update', para);
                this.dialogFormVisible = false; // 关闭编辑界面
                // this.$ajax.post('apis/category/update', this.selectTable);
            },
            // 编辑分类
            handleEdit(row) {
                console.log(row);
                this.selectTable = row;
                this.dialogFormVisible = true;
            },
            // 删除分类
            handleDelete(index, row) {
                this.$ajax.get('apis/category/delete', {params: {id: row.id}});
                setTimeout(() => {
                    this.initData()
                }, 500);
            },
            // 初始化数据，得到全部分类
            initData() {
                var _this = this;
                this.$ajax.post('apis/category/getAll').then(function (res) {
                    _this.tableData = res.data;
                });
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
