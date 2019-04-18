;(function () {
    'use strict';
    var element = $('#updateUserConfirmDialog');
    var UserConfirmDialog = {
        handlingModalOnClosing: function () {
            element.on('hide.bs.modal', function () {
                var activeElement = $(document.activeElement);
                if(!activeElement.is('[data-dismiss]')) {
                    $('#updateUserSuccessDialog').modal('show');
                }
            });
        },

        handlingConfirmUpdateUserOnClick: function () {
            element.on('click', '.btn-confirm-update', function () {
            var check = $('#isActive').is(':checked');
            var isActive;
            if(check){
                isActive = 1;
            }
            else{
                isActive = 0;
            }

            var roles = [];
            var role = {};
            role.id = parseInt($('#settingRole').find('option:selected').val());
            role.name = $('#settingRole').find('option:selected').text();
            roles[0] = role;

            var data = {
                firstName: $('#settingFirstName').val(),
                lastName: $('#settingLastName').val(),
                isActive: isActive,
                email: $('#settingEmail').val(),
                username: username,
                password: '',
                roles: roles
            };
            $.ajax(gbs.ajax.api.post('/user/save', data)).then(function() {
                    element.modal('hide');
                    $('#updateUserSuccessDialog').modal('show');
                }).fail(function(xhr) {
                    gbs.announceApiErrorResponse(xhr);
                });
            });
        },

        run: function () {
            this.handlingConfirmUpdateUserOnClick();
            this.handlingModalOnClosing();
        }
    }
    $(document).ready(function () {
        UserConfirmDialog.run();
    });
})();