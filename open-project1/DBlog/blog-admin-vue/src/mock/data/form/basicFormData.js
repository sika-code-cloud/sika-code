const targetOptions =
  [
    {
      label: '公开',
      value: 'public'
    },
    {
      label: '部分公开',
      value: 'partPublic'
    },
    {
      label: '不公开',
      value: 'unPublic'
    }
  ]
const inputData = {
  title: null,
  targetDesc: null,
  measurementCriteria: null,
  customer: null,
  evaluator: null,
  weight: null,
  publices: null,
  startAndEndDate: null
}
export default {
  targetOptions,
  inputData
}
