function getRandomData(datas) {
  return datas[Math.floor(Math.random() * datas.length)]
}

function getRandomRangeInt(minValue, maxValue) {
  return getRandomFloorInt(maxValue) + minValue
}

function getRandomFloorInt(maxValue) {
  return Math.floor(Math.random() * maxValue)
}

function getRandomCeilInt(maxValue) {
  return Math.ceil(Math.random() * maxValue)
}
export default {
  getRandomData,
  getRandomRangeInt,
  getRandomCeilInt,
  getRandomFloorInt
}
