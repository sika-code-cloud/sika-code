<template>
  <div class="q-pa-md q-gutter-sm">
    <q-editor
      v-model="editor"
      min-height="5rem"
    />

    <q-card
      flat
      bordered
    >
      <q-card-section>
        <pre style="white-space: pre-line">{{ editor }}</pre>
      </q-card-section>
    </q-card>

    <q-card
      flat
      bordered
    >
      <q-card-section v-html="editor" />
    </q-card>
    <q-separator />
    <q-editor
      v-model="editor"
      :definitions="{
        bold: {label: 'Bold', icon: null, tip: 'My bold tooltip'}
      }"
    />
    <q-separator />
    <q-editor
      v-model="editor"
      :definitions="{
        save: {
          tip: 'Save your work',
          icon: 'save',
          label: 'Save',
          handler: saveWork
        },
        upload: {
          tip: 'Upload to cloud',
          icon: 'cloud_upload',
          label: 'Upload',
          handler: uploadIt
        }
      }"
      :toolbar="[
        ['bold', 'italic', 'strike', 'underline'],
        ['upload', 'save']
      ]"
    />
    <q-separator />
    <q-editor
      v-model="editor"
      :dense="$q.screen.lt.md"
      :toolbar="[
        [
          {
            label: $q.lang.editor.align,
            icon: $q.iconSet.editor.align,
            fixedLabel: true,
            list: 'only-icons',
            options: ['left', 'center', 'right', 'justify']
          },
          {
            label: $q.lang.editor.align,
            icon: $q.iconSet.editor.align,
            fixedLabel: true,
            options: ['left', 'center', 'right', 'justify']
          }
        ],
        ['bold', 'italic', 'strike', 'underline', 'subscript', 'superscript'],
        ['token', 'hr', 'link', 'custom_btn'],
        ['print', 'fullscreen'],
        [
          {
            label: $q.lang.editor.formatting,
            icon: $q.iconSet.editor.formatting,
            list: 'no-icons',
            options: [
              'p',
              'h1',
              'h2',
              'h3',
              'h4',
              'h5',
              'h6',
              'code'
            ]
          },
          {
            label: $q.lang.editor.fontSize,
            icon: $q.iconSet.editor.fontSize,
            fixedLabel: true,
            fixedIcon: true,
            list: 'no-icons',
            options: [
              'size-1',
              'size-2',
              'size-3',
              'size-4',
              'size-5',
              'size-6',
              'size-7'
            ]
          },
          {
            label: $q.lang.editor.defaultFont,
            icon: $q.iconSet.editor.font,
            fixedIcon: true,
            list: 'no-icons',
            options: [
              'default_font',
              'arial',
              'arial_black',
              'comic_sans',
              'courier_new',
              'impact',
              'lucida_grande',
              'times_new_roman',
              'verdana'
            ]
          },
          'removeFormat'
        ],
        ['quote', 'unordered', 'ordered', 'outdent', 'indent'],

        ['undo', 'redo'],
        ['viewsource']
      ]"
      :fonts="{
        arial: 'Arial',
        arial_black: 'Arial Black',
        comic_sans: 'Comic Sans MS',
        courier_new: 'Courier New',
        impact: 'Impact',
        lucida_grande: 'Lucida Grande',
        times_new_roman: 'Times New Roman',
        verdana: 'Verdana'
      }"
    />
    <q-separator />
    <div class="q-pa-md q-gutter-sm">
      <q-editor
        v-model="editor"
        ref="editor"
        toolbar-text-color="white"
        toolbar-toggle-color="yellow-8"
        toolbar-bg="primary"
        :toolbar="[
        ['token'],
        ['bold', 'italic', 'underline'],
        [{
          label: $q.lang.editor.formatting,
          icon: $q.iconSet.editor.formatting,
          list: 'no-icons',
          options: ['p', 'h3', 'h4', 'h5', 'h6', 'code']
        }]
      ]"
      >
        <template v-slot:token>
          <q-btn-dropdown
            dense
            no-caps
            ref="token"
            no-wrap
            unelevated
            color="white"
            text-color="primary"
            label="Token"
            size="sm"
          >
            <q-list dense>
              <q-item
                tag="label"
                clickable
                @click="add('email')"
              >
                <q-item-section side>
                  <q-icon name="mail" />
                </q-item-section>
                <q-item-section>Email</q-item-section>
              </q-item>
              <q-item
                tag="label"
                clickable
                @click="add('title')"
              >
                <q-item-section side>
                  <q-icon name="title" />
                </q-item-section>
                <q-item-section>Title</q-item-section>
              </q-item>
            </q-list>
          </q-btn-dropdown>
        </template>
      </q-editor>
    </div>
  </div>
</template>
<script>
export default {
  data () {
    return {
      editor: 'What you see is <b>what</b> you get.'
    }
  },
  methods: {
    saveWork () {
      this.$q.notify({
        message: 'Saved your text to local storage',
        color: 'green-4',
        textColor: 'white',
        icon: 'cloud_done'
      })
    },
    uploadIt () {
      this.$q.notify({
        message: 'Server unavailable. Check connectivity.',
        color: 'red-5',
        textColor: 'white',
        icon: 'warning'
      })
    },
    add (name) {
      const edit = this.$refs.editor
      this.$refs.token.hide()
      edit.caret.restore()
      edit.runCmd('insertHTML', `&nbsp;<div class="editor_token row inline items-center" contenteditable="false">&nbsp;<span>${name}</span>&nbsp;<i class="q-icon material-icons cursor-pointer" onclick="this.parentNode.parentNode.removeChild(this.parentNode)">close</i></div>&nbsp;`)
      edit.focus()
    }
  }
}
</script>
<style lang="sass">
.editor_token
  background: rgba(0, 0, 0, .6)
  color: white
  padding: 3px
  &, .q-icon
    border-radius: 3px
  .q-icon
    background: rgba(0, 0, 0, .2)
</style>