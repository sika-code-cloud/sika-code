import request from 'utils/request'

// 登录方法
export function login () {
  const data = {
    username: 'admin',
    password: '123456'
  }
  return request({
    url: '/auth/login',
    method: 'post',
    data: data
  })
}
