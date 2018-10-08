
$(function(){
    vm.init();
});

var vm = new Vue({
    el: '#app',
    data: {
        categoryTree:{},
        langType:'english'
    },
    methods: {
        init: function () {
            debugger;

            $.ajax({
                url: "/cont/contCategory/treeInfo",
                type: "post",
                data: {
                    langType: vm.langType
                },
                success: function (data) {
                    vm.categoryTree=data;
                }
            });



            /*$.ajax({
                type: "GET",
                url: "/cont/contCategory/treeInfo?langType="+vm.langType ,
                dataType: "json",
                success: function (data) {
                    vm.categoryTree=data;
                }
            });*/
        } ,
        save: function () {
            var contProductParamList=[];
            for(i=0;i<vm.rows.length;i++){
                contProductParamList=contProductParamList.concat(vm.rows[i].contProductParamDOList);
            }
            $.ajax({
                type: "POST",
                url: "/cont/contProduct/saveParams",
                data: {contProductParam: JSON.stringify(contProductParamList),contProductId:vm.contProductId},
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {

                        parent.layer.msg("保存成功!");
                        var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                        parent.layer.close(index);

                    }
                }
            });
        },
        removeNewPk:function (pkIndex) {
            vm.newParam.contProductPkDOList.splice(pkIndex,1);
        },
        addNewPk:function () {
            if(vm.newValue){
                var pkDO={};
                pkDO.paramValue=vm.newValue;
                vm.newParam.contProductPkDOList.push(pkDO);
                vm.newValue='';
            }
        },
        addNewParam:function (index) {
            vm.newParam.langType=vm.rows[index].langType;
            vm.newParam.contProductId=vm.contProductId;
            vm.rows[index].contProductParamDOList.push(vm.newParam);
            var newParamTemp={contProductPkDOList:[]};
            vm.newParam=newParamTemp;
            vm.newValue='';
        },
        removeParam:function (index,paramIndex) {
            vm.rows[index].contProductParamDOList.splice(paramIndex,1);
            var newParamTemp={contProductPkDOList:[]};
            vm.newParam=newParamTemp;
            vm.newValue='';
        },
        removePk:function (index,paramIndex,pkIndex) {
            vm.rows[index].contProductParamDOList[paramIndex].contProductPkDOList.splice(pkIndex,1);
        }
    }
});