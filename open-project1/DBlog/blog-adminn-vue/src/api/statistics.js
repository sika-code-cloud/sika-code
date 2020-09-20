import request from '@/utils/request'

const articleApi = {
  RecentWeekLog: '/statistics/recentWeekLog',
  SiteInfo: '/statistics/siteInfo'
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
export function recentWeekLog () {
  return request({
    url: articleApi.RecentWeekLog,
    method: 'post'
  })
}
export function siteInfo () {
  return request({
    url: articleApi.SiteInfo,
    method: 'post'
  })
}
