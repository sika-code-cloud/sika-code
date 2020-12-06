<template>
  <div class="tinymce-box">
    <editor
      v-model="myValue"
      :init="init"
      :disabled="disabled"
      @onClick="onClick"
    >
    </editor>
  </div>
</template>

<script>
import tinymce from 'tinymce/tinymce' // tinymce默认hidden，不引入不显示
import Editor from '@tinymce/tinymce-vue'
import 'tinymce/icons/default/icons'
import 'tinymce/themes/silver'
// 编辑器插件plugins
// 更多插件参考：https://www.tiny.cloud/docs/plugins/
import 'tinymce/plugins/image' // 插入上传图片插件
import 'tinymce/plugins/media' // 插入视频插件
import 'tinymce/plugins/table' // 插入表格插件
import 'tinymce/plugins/lists' // 列表插件
import 'tinymce/plugins/wordcount' // 字数统计插件
export default {
  components: {
    editor: Editor
  },
  name: 'tinymce',
  props: {
    value: {
      type: String,
      default: ''
    },
    disabled: {
      type: Boolean,
      default: false
    },
    plugins: {
      type: [String, Array],
      default: 'lists image media table wordcount'
    },
    toolbar: {
      type: [String, Array],
      default:
        'undo redo |  formatselect | fontsizeselect  fontselect | bold italic forecolor backcolor | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | lists image table | removeformat'
    }
  },
  data() {
    return {
      init: {
        language_url: '/tinymce/langs/zh_CN.js',
        language: 'zh_CN',
        skin_url: '/tinymce/skins/ui/oxide',
        // skin_url: 'tinymce/skins/ui/oxide-dark',//暗色系
        font_formats:
          '微软雅黑=Microsoft YaHei,Helvetica Neue,PingFang SC,sans-serif;苹果苹方=PingFang SC,Microsoft YaHei,sans-serif;宋体=simsun,serif',
        fontsize_formats: '11px 12px 14px 16px 18px 24px 36px 48px',
        height: 300,
        plugins: this.plugins,
        toolbar: this.toolbar,
        branding: false,
        menubar: false,
        convert_urls: false,
        // images_upload_base_path: process.env.BASE_URL + '/test/',
        // relative_urls : false,
        // remove_script_host : true,
        // document_base_url: location.protocol + '//' + location.hostname,
        // 此处为图片上传处理函数，这个直接用了base64的图片形式上传图片，
        // 如需ajax上传可参考https://www.tiny.cloud/docs/configure/file-image-upload/#images_upload_handler
        images_upload_handler: (blobInfo, success, failure) => {
          const img = 'data:image/jpeg;base64,' + blobInfo.base64()
          success(img)
        },
        urlconverter_callback: function (url, node, on_save, name) {
          return url
        },
        setup: (editor) => {
          editor.on('blur', () => {
            this.$emit('onBlur', this.myValue)
          })
        }
      },
      myValue: this.value
    }
  },
  mounted() {
    tinymce.init({})
  },
  methods: {
    // 添加相关的事件，可用的事件参照文档=> https://github.com/tinymce/tinymce-vue => All available events
    // 需要什么事件可以自己增加
    onClick(e) {
      this.$emit('onClick', e, tinymce)
    },
    // 可以添加一些自己的自定义事件，如清空内容
    clear() {
      this.myValue = ''
    },
    getWordcount() {
      return tinymce.activeEditor.plugins.wordcount
    },
    // 自定义方法，判断编辑器内是否有有效内容
    isNoEmpty() {
      const wordcount = this.getWordcount()
      const reg1 = /<table.*?>[\s\S]*<\/table>/
      const reg2 = /<ul.*?>[\s\S]*<\/ul>/
      const reg3 = /<ol.*?>[\s\S]*<\/ol>/
      const reg4 = /<img.*?>/
      const count = wordcount.body.getWordCount()

      return (
        reg1.test(this.myValue) ||
        reg2.test(this.myValue) ||
        reg3.test(this.myValue) ||
        reg4.test(this.myValue) ||
        count > 0
      )
    }
  },
  watch: {
    value(newValue) {
      this.myValue = newValue
    },
    myValue(newValue) {
      this.$emit('input', newValue)
    }
  }
}
</script>
<style scoped></style>
