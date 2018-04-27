<template>
    <div>
        <head-top></head-top>
        <el-input v-model="id" v-if="false"/>
        <el-input v-model="title" placeholder="标题"/>
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
            submit() {
                let article = {
                    title: this.title,
                    content: this.content,
                    userId: sessionStorage.getItem('user')
                };
                this.$ajax.post('/apis/article/post', article);
                console.log(this.content);
                this.$message.success('提交成功！');
            },
            getArticle() {
                console.log('执行');
                console.log(this.$route);
                if (this.$route.query !== undefined) {
                    console.log(this.$route.query.article_id);
                    if (this.$route.query.article_id !== undefined) {
                        let article = {
                            id: this.$route.query.article_id
                        };
                        var _this = this;
                        this.$ajax.post('apis/article/get', article).then(function (res) {
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

    .el-input, .el-input__inner {

        width: 100%;
        display: inline-block;
        width: 400px;
        margin-left: 150px;
        margin-top: 20px;

    }
</style>
