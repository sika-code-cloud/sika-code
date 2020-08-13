<template>
  <div class="app-container">
    基础用法：<br><br>
    <div class="block">
      <div class="radio">
        排序：
        <el-radio-group v-model="base.reverse">
          <el-radio :label="true">倒序</el-radio>
          <el-radio :label="false">正序</el-radio>
        </el-radio-group>
      </div>
      <br>
      <el-timeline :reverse="base.reverse">
        <el-timeline-item v-for="(activity, index) in base.activities" :key="index" :timestamp="activity.timestamp">
          {{ activity.content }}
        </el-timeline-item>
      </el-timeline>
    </div>
    自定义节点样式：<br><br>
    <div class="block">
      <div class="radio">
        排序：
        <el-radio-group v-model="custom.reverse">
          <el-radio :label="true">倒序</el-radio>
          <el-radio :label="false">正序</el-radio>
        </el-radio-group>
      </div>
      <br>
      <el-timeline :reverse="custom.reverse">
        <el-timeline-item
          v-for="(activity, index) in custom.activities"
          :key="index"
          :icon="activity.icon"
          :type="activity.type"
          :color="activity.color"
          :size="activity.size"
          :timestamp="activity.timestamp"
          :placement="activity.placement"
        >
          <el-card>
            {{ activity.content }}
          </el-card>
        </el-timeline-item>
      </el-timeline>
    </div>

    自定义时间戳：<br><br>
    <div class="block">
      <el-timeline>
        <el-timeline-item
          v-for="(activitiy, index) in customTimestamp.activities"
          :key="index"
          :type="activitiy.type"
          :timestamp="activitiy.timestamp"
          :placement="activitiy.placement"
        >
          <el-card>
            <h4>{{ activitiy.title }}</h4>
            <p>{{ activitiy.content }}</p>
          </el-card>
        </el-timeline-item>
      </el-timeline>
    </div>
  </div>
</template>

<script>
export default {
  name: 'TimeLine',
  data() {
    return {
      base: {
        reverse: true,
        activities: [{
          content: '活动按期开始',
          timestamp: '2018-04-15'
        }, {
          content: '通过审核',
          timestamp: '2018-04-13'
        }, {
          content: '创建成功',
          timestamp: '2018-04-11'
        }]
      },
      custom: {
        reverse: true,
        activities: [{
          content: '支持使用图标',
          timestamp: '2018-04-12 20:46:00',
          size: 'large',
          type: 'primary',
          icon: 'el-icon-more',
          placement: 'top'
        }, {
          content: '支持自定义颜色',
          timestamp: '2018-04-11 20:46:00',
          color: '#0bbd87',
          placement: 'top'
        }, {
          content: '支持自定义尺寸',
          timestamp: '2018-04-10 20:46:00',
          size: 'large',
          placement: 'top'
        }, {
          content: '默认样式的节点',
          timestamp: '2018-04-08 20:46:00',
          placement: 'top'
        }]
      },
      customTimestamp: {
        activities: [{
          title: '更新 Github 模板',
          content: '王小虎 提交于 2018/4/12 20:46',
          timestamp: '2018-04-12 20:46:00',
          size: 'large',
          type: 'primary',
          icon: 'el-icon-more',
          placement: 'top'
        }, {
          title: '更新 Github 模板',
          content: '王小虎 提交于 2018/4/12 20:46',
          timestamp: '2018-04-11 20:46:00',
          color: '#0bbd87',
          placement: 'top'
        }, {
          title: '更新 Github 模板',
          content: '王小虎 提交于 2018/4/12 20:46',
          timestamp: '2018-04-10 20:46:00',
          size: 'large',
          type: 'success',
          placement: 'top'
        }, {
          title: '默认样式的节点',
          content: '王小虎 提交于 2018/4/12 20:46',
          timestamp: '2018-04-08 20:46:00',
          type: 'warning ',
          placement: 'top'
        }]
      }
    }
  }
}
</script>
