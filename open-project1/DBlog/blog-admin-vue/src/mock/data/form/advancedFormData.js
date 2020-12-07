const storehouseData = {
  name: null,
  domain: null,
  admin: null,
  approval: null,
  effectiveDate: null,
  type: null
}
const taskData = {
  name: null,
  desc: null,
  executor: null,
  personLiable: null,
  effectiveDate: null,
  type: null
}

const memberData = {
  columns: [
    {
      name: 'name',
      required: true,
      label: '成员姓名',
      align: 'left',
      field: 'name'
    },
    {
      name: 'jobNumber',
      align: 'left',
      label: '工号',
      field: 'jobNumber'
    },
    {
      name: 'department',
      align: 'left',
      label: '所属部门',
      field: 'department'
    },
    {
      name: 'operation',
      label: '操作',
      align: 'center'
    }
  ],
  columnDatas: [
    {
      index: 0,
      edit: false,
      name: 'John Brown',
      jobNumber: '0001',
      department: 'Siake Park'
    },
    {
      index: 1,
      edit: false,
      name: 'Jim Green',
      jobNumber: '0002',
      department: 'London No.'
    },
    {
      index: 2,
      edit: false,
      name: 'Joe Black',
      jobNumber: '0003',
      department: 'Sidney No. 2'
    },
    {
      index: 3,
      edit: false,
      name: 'Joe Black Ml',
      jobNumber: '0004',
      department: 'New 1 Lake Park'
    },
    {
      index: 4,
      edit: false,
      name: 'Joe Black Rt',
      jobNumber: '0005',
      department: 'Sidney No'
    }
  ],
  columnDataDefault: {
    index: null,
    edit: true,
    name: null,
    jobNumber: null,
    department: null
  }
}
export default {
  storehouseData,
  taskData,
  memberData
}
