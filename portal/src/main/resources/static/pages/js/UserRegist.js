; (function () {
    'use strict';

    var UserRegist = {
        validate: function () {

            $('#frm').validate({
                rules: {
                    settingFirstName: {
                        required: true,
                        rangelength: [1, 256]
                    },
                    settingLastName: {
                        required: true,
                        rangelength: [1, 256]
                    },
                    settingEmail: {
                        required: true,
                        email: true
                    },
                    settingUsername: {
                        required: true,
                        rangelength: [1, 256]
                    },
                    settingPassword: {
                        required: true,
                        minlength : 5
                    },
                    settingConfirmPW: {
                        required: true,
                        equalTo: "#settingPassword",
                    },
                    settingRole: {
                        required: true,
                    }

                },
                messages: {
                    settingLastName: {
                        required: gbs.messages.requiredErrorMessage,
                        rangelength: gbs.messages.rangeLengthErrorMessage
                    },
                    settingFirstName: {
                        required: gbs.messages.requiredErrorMessage,
                        rangelength: gbs.messages.rangeLengthErrorMessage
                    },
                    settingUsername: {
                        required: gbs.messages.requiredErrorMessage,
                        rangelength: gbs.messages.rangeLengthErrorMessage
                    },
                    settingPassword: {
                        required: gbs.messages.requiredErrorMessage,
                        minlength: gbs.messages.settingPassword
                    },
                    settingConfirmPW: {
                        required: gbs.messages.requiredErrorMessage,
                        equalTo: gbs.messages.settingConfirmPW
                    },
                    settingEmail: {
                        required: gbs.messages.requiredErrorMessage,
                        email: gbs.messages.settingEmail
                    },
                    settingRole: {
                        required: gbs.messages.requirePleaseSelect,
                    }
                }
            });

        },

        getFormData: function () {
            var roles = [], role = {}, isActive, data= {};
            role.id = parseInt($('#settingRole').find('option:selected').val());
            role.name = $('#settingRole').find('option:selected').text();
            roles[0] = role;

             isActive = (role.id == 1) ? 1 : 0;

             data = {
                firstName: $('#settingFirstName').val(),
                lastName: $('#settingLastName').val(),
                email: $('#settingEmail').val(),
                username: $('#settingUsername').val(),
                password: $('#settingConfirmPW').val(),
                roles: roles,
                isActive: isActive
            };
            return data;
        },


        handlingRegistUserOnClick: function () {
            var self = this;
            $('.btn-regist').on('click', function (event) {
                event.preventDefault();
                $('#frm').validate().resetForm();
                var data = self.getFormData();
                if ($('#frm').valid()) {
                    $.ajax(gbs.ajax.api.post('/user/save', data)).then(function (flag) {
                        if(flag == 1){
                            $('#createUserSuccessDialog').modal('show');
                            $('#createUserSuccessDialog').find('#uname').text(data.username);
                        } else {
                            $('#error').text(gbs.messages.usernameDuplicate);
                        }
                    }).fail(function (xhr) {
                      gbs.announceApiErrorResponse(xhr);
                  });
                }
            });
        },

        setup: function () {
            this.validate();
        },

        run: function () {
            this.setup();
            this.handlingRegistUserOnClick();
        }
    }

    $(document).ready(function () {
        UserRegist.run();
    });
})();