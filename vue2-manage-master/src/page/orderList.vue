<template>
    <div class="fillcontain">
        <head-top></head-top>
        <div class="table_container">
            <el-table
                :data="tableData"
                :expand-row-keys='expendRow'
                style="width: 100%">
                <el-table-column
                    prop="id"
                    v-if="false"
                />
                <el-table-column
                    label="文章名"
                    prop="article">
                </el-table-column>
                <el-table-column
                    label="评论人"
                    prop="nickname"/>
                <el-table-column
                    label="评论"
                    prop="comment">
                </el-table-column>
                <el-table-column
                    label="发布时间"
                    prop="publishTime"
                    :formatter="dateFormat">
                </el-table-column>
                <el-table-column label="操作" width="200">
                    <template scope="scope">
                        <el-button
                            size="mini"
                            type="danger"
                            @click="handleDelete(scope.$index, scope.row)">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="Pagination" style="text-align: left;margin-top: 10px;">
                <el-pagination
                    @size-change=""
                    @current-change=""
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
    import moment from 'moment'

    export default {
        data() {
            return {
                id: '',
                tableData: [],
                currentRow: null,
                offset: 0,
                limit: 20,
                count: 0,
                currentPage: 1,
                restaurant_id: null,
                expendRow: [],
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
            getComments() {
                var _this = this;
                this.$ajax.post('apis/comment/getAll').then(function (res) {
                    _this.tableData = res.data;
                })
            },
            handleDelete(index, row) {
                let comment = {
                    id: row.id
                };
                this.$ajax.post('apis/comment/delete', comment);
                setTimeout(() => {
                    this.getComments();
                }, 500);
            }
        },
        mounted: function () {
            this.getComments();
        }
    }
</script>

<style lang="less">
    @import '../style/mixin';

    .table_container {
        padding: 20px;
    }

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
</style>
