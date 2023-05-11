<template>
    <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="username" label="Name" width="180" />
        <el-table-column prop="password" label="password" width="180" />
        <el-table-column prop="birthday" label="birthday" />
    </el-table>
</template>
  
<script>
export default {
    name: "table",
    data: function () {
        return {
            tableData: []
        }
    },
    created: function(){
        this.$http.get("/user/findAll").then((response)=>{
            this.tableData = response.data
        })
    }
}
</script>
  