function doProcess(url) {
    $.getJSON(url ,
        function(data){
            data = data.ajaxInfoModel;
            if(data.status=='n'){
                alert(data.info);
            }else {
                window.location.href=window.location.href;
            }
        }
    );
}