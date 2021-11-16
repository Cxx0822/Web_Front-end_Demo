<template>
  <div class="app-container">
    <div class="div-table">
      <el-table
        v-loading="listLoading"
        :data="list"
        style="width:855px"
        element-loading-text="Loading"
        border
        fit
        highlight-current-row
      >
        <el-table-column align="center" label="ID" width="100">
          <template slot-scope="scope">
            {{ scope.$index }}
          </template>
        </el-table-column>

        <el-table-column label="姓名" width="150" align="center">
          <template slot-scope="scope">
            {{ scope.row.name }}
          </template>
        </el-table-column>

        <el-table-column label="年龄" width="150" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.age }}</span>
          </template>
        </el-table-column>

        <el-table-column label="创建时间" width="150" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.createTime }}</span>
          </template>
        </el-table-column>

        <el-table-column label="更新时间" width="150" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.updateTime }}</span>
          </template>
        </el-table-column>

        <el-table-column label="操作" width="150" align="center">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-edit"
              circle
              @click="handleEdit(scope.$index, scope.row)"
            />
            <el-button
              type="danger"
              icon="el-icon-delete"
              circle
              @click="handleDelete(scope.$index, scope.row)"
            />
          </template>
        </el-table-column>
      </el-table>

      <el-dialog title="用户信息" :visible.sync="dialogUpdateUserInfoFormVisible">
        <el-form :model="userInfo">
          <el-form-item label="姓名" label-width="120px">
            <el-input v-model="userInfo.name" autocomplete="off" :disabled="true" />
          </el-form-item>
          <el-form-item label="年龄" label-width="120px">
            <el-input v-model="userInfo.age" autocomplete="off" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogUpdateUserInfoFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="updateUserInfo()">确 定</el-button>
        </div>
      </el-dialog>
    </div>

    <div>
      <el-button type="primary" style="margin-top:10px" @click="handleRegister">注册用户</el-button>
      <el-dialog title="用户信息" :visible.sync="dialogRegisterUserInfoFormVisible">
        <el-form :model="userInfo">
          <el-form-item label="姓名" label-width="120px">
            <el-input v-model="userInfo.name" autocomplete="off" />
          </el-form-item>
          <el-form-item label="年龄" label-width="120px">
            <el-input v-model="userInfo.age" autocomplete="off" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogRegisterUserInfoFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="registerUserInfo()">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import { getList, register, deleteByName, updateByName } from '@/api/user_info'

export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'gray',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      list: null,
      listLoading: true,

      dialogUpdateUserInfoFormVisible: false,
      dialogRegisterUserInfoFormVisible: false,

      userInfo: {
        'name': null,
        'age': null
      }
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getList().then(response => {
        this.list = response.data.userInfoList
        this.listLoading = false
      })
    },
    handleEdit(index, row) {
      this.dialogUpdateUserInfoFormVisible = true
      this.userInfo['name'] = row.name
    },
    updateUserInfo() {
      this.dialogUpdateUserInfoFormVisible = false
      updateByName(this.userInfo.name, this.userInfo).then(response => {
        this.$message.success('修改成功')
        this.userInfo['age'] = null
        this.fetchData()
      })
    },
    handleDelete(index, row) {
      this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteByName(row.name).then(response => {
          this.$message.success('删除成功')
          this.fetchData()
        })
      }).catch(() => {
        this.$message.info('已取消删除')
      })
    },
    handleRegister() {
      this.dialogRegisterUserInfoFormVisible = true
    },
    registerUserInfo() {
      this.dialogRegisterUserInfoFormVisible = false
      register(this.userInfo).then(response => {
        this.$message.success('注册成功')
        this.userInfo['name'] = null
        this.userInfo['age'] = null
        this.fetchData()
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.el-table{
  border: 2px solid #008B93;
}
</style>
