const pictureSrcs = [
  'https://gw.alipayobjects.com/zos/rmsportal/uMfMFlvUuceEyPpotzlq.png',
  'https://gw.alipayobjects.com/zos/rmsportal/iZBVOIhGJiAnhplqjvZW.png',
  'https://pic.rmb.bdstatic.com/fbb6939952e2e27c5cdfc5b706905768.jpeg@wm_2,t_55m+5a625Y+3L+WIhuS6q+e+juS4veeahOmbhuS4reWcsA==,fc_ffffff,ff_U2ltSGVp,sz_26,x_17,y_17',
  'https://gw.alipayobjects.com/zos/rmsportal/iXjVmWVHbCJAyqvDxdtx.png',
  'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604603259601&di=98453cd17ded14a25ec805df888b2267&imgtype=0&src=http%3A%2F%2Fimg.08087.cc%2Fuploads%2F20191128%2F14%2F1574923905-WqopanrGSZ.jpg',
  'https://gw.alipayobjects.com/zos/rmsportal/gLaIAoVWTtLbBWZNYEMg.png',
  'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604602939538&di=a457faa41c62fff3cbf0ab1fed08dcfc&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fblog%2F201308%2F05%2F20130805105309_5E2zE.jpeg',
  'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604602939540&di=329ba6679a8ac3e9feb457c5dd24e3d5&imgtype=0&src=http%3A%2F%2F00.minipic.eastday.com%2F20170415%2F20170415170933_d0304d15b93b7025b2a045c4ff65baf3_13.jpeg',
  'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604602941634&di=be2c1a81c94d87340186413a8b1bd42e&imgtype=0&src=http%3A%2F%2Ft1.hxzdhn.com%2Fuploads%2Ftu%2F201807%2F9999%2Fb488d1a5fc.jpg'
]
const projectDatas = []
for (let i = 0; i < 19; ++i) {
  const pictureIndex = Math.floor(Math.random() * pictureSrcs.length)
  projectDatas.push({
    pictureSrc: pictureSrcs[pictureIndex],
    index: i,
    hour: Math.ceil(Math.random() * 20)
  })
}
export default {
  projectDatas
}
