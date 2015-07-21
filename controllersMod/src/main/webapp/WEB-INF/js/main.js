function apply_login()
{ 
    if ($('.login-form').size())
    {
        $('.login-form').dialog({ autoOpen: false, title: titleAuth, modal: true, resizable: false, dialogClass: 'login-form-wrap', width: 320 });
        $('a.login').click(function(){
            $('.login-form span.info').html('');
            $('.login-form .results-block').slideUp('fast');
            $('#loginform #login').val('');
            $('#loginform #password').val('');
            $('.login-form').dialog('open');
        });

        $('.login-form').find('.my-close').click(function(){
            $('.login-form').dialog('close');
        });
    }
}


function remove_item() {
    if ($('.remove-dialog').size())
    {
        $('.remove-dialog').dialog({ autoOpen: false, title: titleRemove, modal: true, resizable: false, dialogClass: 'login-form-wrap', width: 320 });
        $('.delete').click(function(e){


            $('.remove-dialog span.info').html('');
            $('.remove-dialog .text').text('');
            $('.remove-dialog .question').text('');
            var type = typeOfActivities[0];
            var finishing = categoryGood[0];
            var additionally = help[0];
            var td_ = $(this).parent().parent().children('td').eq(1);
            var cont = $(this).attr('href').split('/');
            if ( cont[0] == 'product' ) {
                type = typeOfActivities[1];
                finishing = categoryGood[1];
                additionally = help[1];
                var td_ = $(this).parent().parent().parent().find('h3.title');
            } 
            
            
            $('.remove-dialog .text').text(sprintf(textRemove,type,td_.text().trim(),additionally));
            $('.remove-dialog .question').text(sprintf(questionRemove,finishing,type));
            $('#remove').attr('href',$(this).attr('href'));
            $('.remove-dialog').dialog('open');
            e.preventDefault();
        });

        $('.remove-dialog').find('.my-close').click(function(){
            $('.remove-dialog').dialog('close');
        });
    }
}

function addPhoto() {
    var p = 0;
    $('a.add-image').live( 'click', function(e) {
        if ($(this).parent().parent().find('input[type="file"], a.photo').size() < 5 ) {
            var id = $(this).attr('id').split('_');
            $(this).parent().append('<input class="file" type="file" name="data[photo' + id[0] + '][' + (++p) + ']"/><a href="javascript:void(0);" class="remove-image" id="' + id[0] + '_' + p +'"></a>');
        } else {
            alert("Нельзя добавлять больше пяти картинок.");
        }
        e.preventDefault();
    });

    $('a.remove-image').live('click', function () {
        var id = $(this).attr('id').split('_');
        $("input[name='data[photo" + id[0] + "][" + id[1] + "]']").remove();
        $(this).remove();
    });

    $('span.remove-img').click(function(e) {
       var div = $(this).closest('div.row');
       if ( div.find('p.add-image').size() < 1 && div.find('input[type="file"], a.photo').size() < 6 ) {
            var id = $(this).attr('id');
            div.append('<p class="add-image"><input class="file required" type="file" name="data[photo' + id + '][0]"/><a id="'  + id + '_0" href="javascript:void(0);" class="add-image"></a></p>');
       }
       $(this).parent().remove();
       e.preventDefault();
    });
}


$(document).ready(function(){
	apply_login();
    remove_item();
    //addPhoto();
    //filter();
    //addPhones();
    


});
