import request from '@/utils/request'

const statiticsApi = {
  RecentWeekLog: '/statistics/recentWeekLog',
  SameYearLog: '/statistics/sameYearLog',
  SameMonthLog: '/statistics/sameMonthLog',
  SameDayLog: '/statistics/sameDayLog',
  SiteInfo: '/statistics/siteInfo',
  ListType: '/statistics/listType',
  ListSpider: '/statistics/listSpider'
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
export function recentWeekLog (parameter) {
  return request({
    url: statiticsApi.RecentWeekLog,
    data: parameter,
    method: 'post'
  })
}

export function sameYearLog (parameter) {
  return request({
    url: statiticsApi.SameYearLog,
    data: parameter,
    method: 'post'
  })
}

export function sameMonthLog (parameter) {
  return request({
    url: statiticsApi.SameMonthLog,
    data: parameter,
    method: 'post'
  })
}

export function sameDayLog (parameter) {
  return request({
    url: statiticsApi.SameDayLog,
    data: parameter,
    method: 'post'
  })
}
export function siteInfo () {
  return request({
    url: statiticsApi.SiteInfo,
    method: 'post'
  })
}
export function listType () {
  return request({
    url: statiticsApi.ListType,
    method: 'post'
  })
}
export function listSpider () {
  return request({
    url: statiticsApi.ListSpider,
    method: 'post'
  })
}
