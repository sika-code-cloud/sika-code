const imgSrcs = [
  'https://gw.alipayobjects.com/zos/rmsportal/WdGqmHpayyMjiEhcKoVE.png',
  'https://cdn.quasar.dev/img/avatar5.jpg',
  'https://gw.alipayobjects.com/zos/rmsportal/zOsKZmFRdUtvpqCImOVY.png',
  'https://gw.alipayobjects.com/zos/rmsportal/dURIMkkrRFpPgTuzkwnB.png',
  'https://gw.alipayobjects.com/zos/rmsportal/sfjbOqnsXXJgNCjCzDBL.png',
  'https://gw.alipayobjects.com/zos/rmsportal/siCrBXXhmvTQGWPNLBow.png',
  'https://gw.alipayobjects.com/zos/rmsportal/kZzEzemZyKLKFsojXItE.png'
]

const cardListDatas = []
for (let i = 0; i < 18; ++i) {
  const imgIndex = Math.floor(Math.random() * imgSrcs.length)
  cardListDatas.push({
    index: i,
    imgSrc: imgSrcs[imgIndex],
    activeUser: Math.ceil(Math.random() * 20),
    increaseUser: Math.floor(Math.random() * 2000) + 1
  })
}
export default {
  cardListDatas
}
