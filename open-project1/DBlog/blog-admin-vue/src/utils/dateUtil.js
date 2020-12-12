function buildRandomDate(year) {
  if (!year) {
    year = Math.floor(Math.random() * 10) + 2010
  }
  const month = Math.floor(Math.random() * 12)
  const day = Math.floor(Math.random() * 30)
  const hour = Math.floor(Math.random() * 24)
  const minute = Math.floor(Math.random() * 60)
  const second = Math.floor(Math.random() * 60)
  return new Date(year, month, day, hour, minute, second)
}
export default {
  buildRandomDate
}
