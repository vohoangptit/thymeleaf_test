;(function () {
    'use strict';
    var element = $('#registNoticeConfirmDialog');

    var NoticeRegistConfirmDialog = {

        validate: function () {
            $('#frmRegistNoticeConfirmDialog').validate({
                rules: {
                    registConfirmTemplateName: {
                        required: true,
                        minlength: 1,
                        maxlength: 256
                    }
                },
                messages: {
                    registConfirmTemplateName: gbs.messages.registConfirmTemplateName
                }
            });
        },

        handlingConfirmRegisterOnClick: function () {
            var self = this;
            element.on('click', '.btn-confirm-register', function () {
                var noticeDTO = self.getFormData();
                if($('#frmRegistNoticeConfirmDialog').valid()) {
                    var templateName = $('#registConfirmTemplateName').val();
                    $.ajax(gbs.ajax.api.post('/notice-template/save/' + templateName, noticeDTO.data)).then(function() {
                        element.modal('hide');
                        $('#registCreateSuccessDialog').modal('show');
                        $('#registCreateSuccessDialog').find('#templateName').text(templateName);
                    }).fail(function(xhr) {
                        gbs.announceApiErrorResponse(xhr);
                    });
                }
            });
        },

        getFormData: function () {
            var portal = $('#settingNoticeMethodPortal').is(':checked'),
                mail = $('#settingNoticeMethodMail').is(':checked'),
                displayTarget = (portal && mail) ? 3 : (mail ? 2 : 1),
                data = {
                    displayDay: $('#settingDisplayRange').val(),
                    title: $('#contentTitleTemplate').val(),
                    notice: $('#contentTextTemplate').val(),
                    displayTarget: displayTarget,
                    classId: 1,
                    settlementWayId: 1
                },
                result = [];

            result.data = data;
            result.portal = portal;
            result.mail = mail;
            return result;
        },

        setup: function() {
            this.validate();
        },

        run: function () {
            this.setup();
            this.handlingConfirmRegisterOnClick();
        }
    }

    $(document).ready(function () {
        NoticeRegistConfirmDialog.run();
    });

    window.RegistNoticeConfirmDialog = NoticeRegistConfirmDialog;
})();