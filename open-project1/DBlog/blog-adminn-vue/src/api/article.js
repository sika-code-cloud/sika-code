import request from '@/utils/request'
import qs from 'qs'

const articleApi = {
  ArticleList: '/article/list'
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
