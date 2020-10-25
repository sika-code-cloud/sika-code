<script>
export default {
  // ...
  meta: {
    // 设置文件标题
    title: 'Index Page',
    // 可选的; 将最终标题设置为“Index Page - My Website”，对于多级meta有用
    titleTemplate: title => `${title} - My Website`,

    // meta标记
    meta: {
      description: { name: 'description', content: 'Page 1' },
      keywords: { name: 'keywords', content: 'Quasar website' },
      equiv: {
        'http-equiv': 'Content-Type',
        content: 'text/html; charset=UTF-8'
      }
    },

    // CSS标记
    link: {
      material: {
        rel: 'stylesheet',
        href: 'https://fonts.googleapis.com/icon?family=Material+Icons'
      }
    },

    // JS标记
    script: {
      ldJson: {
        type: 'application/ld+json',
        innerHTML: '{ "@context": "http://schema.org" }'
      }
    },

    // <html>属性
    htmlAttr: {
      'xmlns:cc': 'http://creativecommons.org/ns#', // 生成<html xmlns:cc="http://creativecommons.org/ns#">,
      empty: undefined // 生成<html empty>
    },

    // <body> attributes
    bodyAttr: {
      'action-scope': 'xyz', // 生成<body action-scope="xyz">
      empty: undefined // 生成<body empty>
    },

    // <noscript>标记
    noscript: {
      default: 'This is content for browsers with no JS (or disabled JS)'
    }
  }
}
</script>
