import request from '@/utils/request'
import qs from 'qs'

const articleApi = {
  ArticleList: '/article/list',
  SystemBrushFlow: '/article/systemBrushFlow',
  ListRecent: '/article/listRecent'
}

/**
 * login func
 * parameter: {
 *     username: '',
 *     password: '',
 *     remember_me: true,
 *     captcha: '12345'
 * }
 * @param parameter
 * @returns {*}
 */
export function articleList (parameter) {
  return request({
    url: articleApi.ArticleList,
    data: qs.stringify(parameter),
    method: 'post'
  })
}
export function systemBrushFlow () {
  return request({
    url: articleApi.SystemBrushFlow,
    method: 'post'
  })
}
export function listRecent () {
  return request({
    url: articleApi.ListRecent,
    method: 'post'
  })
}
