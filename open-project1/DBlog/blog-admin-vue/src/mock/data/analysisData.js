import qDate from 'quasar'
const visitData = {
  icon: 'visibility',
  dayVisitCount: '5,848',
  dayForCompare: '12.5%',
  weekForCompare: '5.0%',
  visitTotal: '280 万'
}
const saleData = {
  icon: 'attach_money',
  saleMoney: '12,000',
  saleForCompare: '60',
  saleTotal: '68 万'
}
const orderData = {
  icon: 'reorder',
  dayOrderCount: '1,680',
  dayForCompare: '12.5%',
  weekForCompare: '8.0%',
  conversionRate: '60 %'
}
const userData = {
  icon: 'people_alt',
  dayIncrease: '128',
  users: [
    {
      head: 'https://dev-file.iviewui.com/BbnuuEiM0QXNPHVCvb3E2AFrawIjCkqW/avatar',
      name: 'Yue Han'
    },
    {
      head: 'https://dev-file.iviewui.com/zhj85zgAfEjChCNIKT1LQENUIOyOYCaX/avatar',
      name: 'Jony'
    },
    {
      head: 'https://dev-file.iviewui.com/TkH54UozsINlex15TAMI00GElsfsKSiC/avatar',
      name: 'Qiao Zhi'
    },
    {
      head: 'https://dev-file.iviewui.com/xrzbBR99F6tYsDJPLNrvwhllowbuL7Gw/avatar',
      name: 'Xiao Xiao'
    },
    {
      head: 'https://dev-file.iviewui.com/bgrngoUb9A6UQ2kAwBFtnSNzhrh2qj1O/avatar',
      name: 'Si Jun'
    },
    {
      head: 'https://dev-file.iviewui.com/bgrngoUb9A6UQ2kAwBFtnSNzhrh2qj1O/avatar',
      name: 'Woney'
    },
    {
      head: 'https://dev-file.iviewui.com/BbnuuEiM0QXNPHVCvb3E2AFrawIjCkqW/avatar',
      name: 'Hellen'
    }
  ],
  userTotal: '10800'
}

/** 函数----begin */
export function getCurrentDate() {
  return new Date().getDate()
}

export function getCurrentMonth() {
  return new Date().getMonth()
}

export function getCurrentYear() {
  return new Date().getFullYear()
}

export function getCurrentDayStart() {
  return getDayStart(new Date())
}
export function getCurrentMonthStart() {
  return getMonthStart(new Date())
}

export function getCurrentYearStart() {
  return getYearStart(new Date())
}

export function getDayStart(date) {
  const startDate = new Date()
  startDate.setHours(0)
  startDate.setMinutes(0)
  startDate.setSeconds(0)
  startDate.setMonth(date.getMonth())
  return startDate
}
export function getMonthStart(date) {
  const startDate = new Date()
  startDate.setDate(1)
  startDate.setHours(0)
  startDate.setMinutes(0)
  startDate.setSeconds(0)
  startDate.setMonth(date.getMonth())
  return startDate
}

export function getYearStart(date) {
  const startDate = new Date()
  startDate.setDate(1)
  startDate.setHours(0)
  startDate.setMinutes(0)
  startDate.setSeconds(0)
  startDate.setDate(1)
  startDate.setMonth(1)
  startDate.setFullYear(date.getFullYear())
  return startDate
}

export function formatDate(date, format) {
  return qDate.formatDate(date, format)
}
/** 函数----end */
export default {
  visitData,
  saleData,
  orderData,
  userData,
  getCurrentDate,
  getCurrentMonth,
  getCurrentYear,
  getCurrentDayStart,
  getCurrentYearStart,
  getCurrentMonthStart,
  getMonthStart,
  getYearStart,
  formatDate
}
