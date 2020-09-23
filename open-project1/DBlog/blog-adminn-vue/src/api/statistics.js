import request from '@/utils/request'

const articleApi = {
  RecentWeekLog: '/statistics/recentWeekLog',
  SameYearLog: '/statistics/sameYearLog',
  SameMonthLog: '/statistics/sameMonthLog',
  SameDayLog: '/statistics/sameDayLog',
  SiteInfo: '/statistics/siteInfo',
  ListType: '/statistics/listType'
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

export function sameYearLog () {
  return request({
    url: articleApi.SameYearLog,
    method: 'post'
  })
}

export function sameMonthLog () {
  return request({
    url: articleApi.SameMonthLog,
    method: 'post'
  })
}

export function sameDayLog () {
  return request({
    url: articleApi.SameDayLog,
    method: 'post'
  })
}
export function siteInfo () {
  return request({
    url: articleApi.SiteInfo,
    method: 'post'
  })
}
export function listType () {
  return request({
    url: articleApi.ListType,
    method: 'post'
  })
}
