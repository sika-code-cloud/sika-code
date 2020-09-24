import request from '@/utils/request'

const statiticsApi = {
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
    url: statiticsApi.RecentWeekLog,
    method: 'post'
  })
}

export function sameYearLog () {
  return request({
    url: statiticsApi.SameYearLog,
    method: 'post'
  })
}

export function sameMonthLog () {
  return request({
    url: statiticsApi.SameMonthLog,
    method: 'post'
  })
}

export function sameDayLog () {
  return request({
    url: statiticsApi.SameDayLog,
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
