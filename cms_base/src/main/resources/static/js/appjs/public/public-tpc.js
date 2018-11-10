
//页脚
var vf = new Vue({
    el: '#footer',
    data: {
        formDO: {},
        formDataDOList: [
            {title: 'Feedback topic *', value: ''},
            {title: 'Name *', value: ''},
            {title: 'E-mail *', value: ''},
            {title: 'Phone *', value: ''},
            {title: 'Information description *', value: ''}
        ],
        errorList:[],
        success:"",
        clicking:false
    },
    methods: {
        submit:function () {
            if(vf.clicking){
                return;
            }else {
                vf.clicking=true;
            }

            if(!vf.validate()){
                return;
            }

            vf.formDO.title="您有新消息了~";

            $.ajax({
                url: "/contTpc/saveInquery",
                type: "post",
                data: {
                    contForm: JSON.stringify(vf.formDO),
                    contFormData:JSON.stringify(vf.formDataDOList)
                },
                success: function (data) {
                    if(data.code==0){
                        //刷新页面
                        vf.clear();
                        vf.clearErrorList();
                        vf.success="Success ！";
                        vf.$forceUpdate();
                        setTimeout(function(){
                            vf.success="";
                            vf.$forceUpdate();
                        },5000);
                        vf.clicking=false;
                    }
                }
            });
        },
        clear:function () {
            var  formDataDOList= [
                {title: 'Feedback topic *', value: ''},
                {title: 'Name *', value: ''},
                {title: 'E-mail *', value: ''},
                {title: 'Phone *', value: ''},
                {title: 'Information description *', value: ''}
            ];
            vf.formDataDOList=formDataDOList;
        },
        clearErrorList:function () {
            vf.errorList.length=0;
            vf.$forceUpdate();
        },
        validate:function () {
            vf.errorList.length=0;
            if(vf.formDataDOList[0].value){
                if(vf.formDataDOList[0].value.length>512){
                    vf.errorList.push("Feedback topic's length out of range !");
                }
            }else {
                vf.errorList.push("Feedback topic can not be empty !");
            }
            if(vf.formDataDOList[1].value){
                if(vf.formDataDOList[1].value.length>512){
                    vf.errorList.push("Name's length out of range !");
                }
            }else {
                vf.errorList.push("Name can not be empty !");
            }
            if(vf.formDataDOList[2].value){
                var re = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/;
                if (!re.test(vf.formDataDOList[2].value)) {
                    vf.errorList.push("Incorrect E-mail format !");
                }
            }else {
                vf.errorList.push("E-mail can not be empty !");
            }
            if(vf.formDataDOList[3].value){
                if(vf.formDataDOList[3].value.length>20){
                    vf.errorList.push("Phone's length out of range !");
                }
            }else {
                vf.errorList.push("Phone can not be empty !");
            }
            if(vf.formDataDOList[4].value){
                if(vf.formDataDOList[4].value.length>2048){
                    vf.errorList.push("Information description's length out of range !");
                }
            }else {
                vf.errorList.push("Information description can not be empty !");
            }

            if(vf.errorList.length>0){
                return false;
            }else{
                return true;
            }
        }
    }
});