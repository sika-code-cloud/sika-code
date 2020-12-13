import commonUtil from 'src/utils/commonUtil'
import _ from 'lodash'

const labelValue = [{
  label: '缺货',
  color: 'orange',
  bgColor: 'bg-orange-1',
  style: 'border: 1px solid orange'
}, {
  label: '推荐',
  color: 'positive',
  bgColor: 'bg-green-1',
  style: 'border: 1px solid #21BA45'
}]
const goodsListDatas = []
const goodsDatasTemp = [
  {
    imgSrc: 'https://dev-file.iviewui.com/yxsk0RFxdR0X3S0N7QN33mvwLnkfHEJV/middle',
    name: 'Vue实战',
    desc: 'Vue.js 作者尤雨溪作序',
    price: 69.50.toFixed(2)
  },
  {
    imgSrc: 'https://dev-file.iviewui.com/PKCycgm3DWJOca5I2uAEqneuLFQAcKa7/middle',
    name: 'Sony 无线立体耳机',
    desc: 'WH-JU8000',
    price: 54.50.toFixed(2)
  },
  {
    imgSrc: 'https://dev-file.iviewui.com/KUa7CaC6m7vRtDCfY0SAXlp7dw9OvBrf/middle',
    name: '京东E卡 (100元)',
    desc: '面值 100 元的京东E卡(电子卡)',
    price: 32.52.toFixed(2)
  },
  {
    imgSrc: 'https://dev-file.iviewui.com/UTPgv8fkOHXszM8Xxm33C2ABuTw7AlRE/middle',
    name: 'Cherry机械键盘背光板',
    desc: 'Cherry Max 2.0',
    price: 21.53.toFixed(2)
  },
  {
    imgSrc: 'https://dev-file.iviewui.com/OYZqqiP1bbwN22Ai2HnwvSagxuSNchdD/middle',
    name: '秒空鼠标3.0 银色',
    desc: 'Apple Mouse 2.0',
    price: 12.40.toFixed(2)
  }
]

for (let i = 0; i < 55; ++i) {
  const goodsDatasT = _.clone(commonUtil.getRandomData(goodsDatasTemp))
  goodsDatasT.id = i
  goodsDatasT.labelData = commonUtil.getRandomData(labelValue)
  goodsListDatas.push(goodsDatasT)
}
export default {
  goodsListDatas
}
