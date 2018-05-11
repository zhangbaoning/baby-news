<template>
    <div>
        <head-top></head-top>
        <el-input v-model="id" v-if="false"/>
        <el-input class="title" v-model="title" placeholder="标题"/>
        <el-input class="descr" type="textarea" v-model="descr" placeholder="简介"/>
        <el-input class="inputImg" v-model="img" placeholder="封面地址">
            <template slot="prepend">Http://</template>
        </el-input>
        <!--内容-->
        <div class="edit_container">
            <quill-editor v-model="content"
                          ref="myQuillEditor"
                          class="editer"
                          :options="editorOption"
                          @ready="onEditorReady($event)">
            </quill-editor>
        </div>
        <div class="submit_btn">
            <el-button type="primary" @click="submit">提交</el-button>
        </div>
    </div>
</template>

<script>
    import headTop from '../components/headTop'
    import {quillEditor} from 'vue-quill-editor'

    export default {
        data() {
            return {
                id: '',
                content: '',
                title: '',
                editorOption: {}
            }
        },
        components: {
            headTop,
            quillEditor,
        },
        computed: {
            editor() {
                return this.$refs.myQuillEditor.quill
            }
        },
        methods: {
            onEditorReady(editor) {
                console.log('editor ready!', editor)
            },
            // 提交
            submit() {
                let article = {
                    id: this.id,
                    title: this.title,
                    content: this.content,
                    userId: sessionStorage.getItem('user'),
                    descr: this.descr,
                    img: this.img
                };
                // 如果ID存在的话进行更新，不存在的话进行新建
                if (this.id) {
                    this.$ajax.post('/apis/article/update', article);
                    this.$message.success('更新成功！');
                } else {
                    console.log('id不存在');
                    this.$ajax.post('/apis/article/post', article);
                    this.$message.success('提交成功！');
                }
            },
            // 从文章管理跳转加载文章
            getArticle() {
                if (this.$route.query !== undefined) {
                    console.log(this.$route.query.article_id);
                    if (this.$route.query.article_id !== undefined) {
                        let article = {
                            id: this.$route.query.article_id
                        };
                        var _this = this;
                        this.$ajax.get('apis/article/get', {params: {articleId: this.$route.query.article_id}}).then(function (res) {
                            console.log(res.data);
                            console.log(res.data.title);
                            _this.title = res.data.title;
                            _this.content = res.data.content;
                            _this.id = res.data.id;

                        });
                    }
                }
            }
        },
        mounted: function () {
            this.getArticle();
        }
    }
</script>

<style lang="less">
    @import '../style/mixin';

    .edit_container {
        padding: 40px;
        margin-bottom: 40px;
    }

    .editer {
        height: 350px;
    }

    .submit_btn {
        text-align: center;
    }

    .title {

        width: 100%;
        display: inline-block;
        width: 400px;
        margin-left: 25%;
        margin-top: 20px;
        margin-bottom: 2%;
    }

    .descr {

        min-height: 33px;
        width: 50%;
        margin-left: 20%;

    }

    .el-input {
        margin-top: 1%;

    }

    .inputImg {

        margin-left: 10%;
        width: 80%;

    }
</style>
