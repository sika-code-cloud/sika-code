import axios from 'axios'
import {
  LoadingBar
} from 'quasar'

axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
// 创建axios实例
const service = axios.create({
  // axios中请求配置有baseURL选项，表示请求URL公共部分
  baseURL: process.env.VUE_APP_BASE_API,
  // 超时
  timeout: 10000
})

// 请求拦截器
service.interceptors.request.use(function (config) {
  return config
}, function (error) {
  return Promise.reject(error)
})
// 响应拦截器
service.interceptors.response.use(function (response) {
  return response
}, function (error) {
  return Promise.reject(error)
})

LoadingBar.setDefaults({
  color: 'light-blue-4',
  size: '4px',
  position: 'top'
})

export default service
