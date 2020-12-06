<template>
  <div class="q-mt-xs bg-white">
    <mavon-editor
      style="z-index: 3000; min-width: 300px"
      v-model="value"
      ref="md"
      @imgAdd="$imgAdd"
      @imgDel="$imgDel"
    ></mavon-editor>
    <!--点击按钮触发图片统一上传-->
    <button @click="uploadimg">upload</button>
    <mavon-editor
      style="z-index: 2000; min-width: 300px"
      v-model="value"
      :editable="false"
      defaultOpen="preview"
      :toolbarsFlag="false"
      :subfield="false"
    ></mavon-editor>
  </div>
</template>

<script>
import { mavonEditor } from 'mavon-editor'
import axios from 'axios'
import 'mavon-editor/dist/css/index.css'

export default {
  name: 'Markdown',
  props: ['comment'],
  components: {
    mavonEditor
  },
  data() {
    return {
      value: '',
      img_file: {}
    }
  },
  mounted() {},
  computed: {},
  methods: {
    // 绑定@imgAdd event
    $imgAdd(pos, $file) {
      // 缓存图片信息
      console.log('pos---------' + pos)
      this.img_file[pos] = $file
    },
    $imgDel(pos) {
      delete this.img_file[pos]
    },
    uploadimg($e) {
      // 第一步.将图片上传到服务器.
      const formDataTemp = new FormData()
      for (const _img in this.img_file) {
        formDataTemp.append(_img, this.img_file[_img])
      }
      axios({
        url: 'server url',
        method: 'post',
        data: formDataTemp,
        headers: { 'Content-Type': 'multipart/form-data' }
      }).then((res) => {
        /**
         * 例如：返回数据为 res = [[pos, url], [pos, url]...]
         * pos 为原图片标志（0）
         * url 为上传后图片的url地址
         */
        // 第二步.将返回的url替换到文本原位置![...](0) -> ![...](url)
        for (var img in res) {
          // $vm.$img2Url 详情见本页末尾
          $vm.$img2Url(img[0], img[1])
        }
      })
    }
  }
}
</script>

<style scoped></style>
