import request from '@/utils/request'

const articleApi = {
  StatisticsRecentWeek: '/article/statisticsRecentWeek'
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
export function statisticsRecentWeek () {
  return request({
    url: articleApi.StatisticsRecentWeek,
    method: 'post'
  })
}
