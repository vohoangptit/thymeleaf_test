; (function () {
    'use strict';

    var UserDetail = {
        validate: function () {

             $('#frm').validate({
                 rules: {
                      settingFirstName: {
                          required: true,
                          rangelength: [1,256]
                      },
                      settingLastName: {
                          required: true,
                          rangelength: [1,256]
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
                     }
                 }
             });
        },

        getFormData: function () {
            var data = {
                firstName: $('#settingFirstName').val(),
                lastName: $('#settingLastName').val(),
                id: $('#settingRole').find('option:selected').val(),
                isActive: $('#IsActive').is(':checked')
            };
            var result = [];
            result.data = data;
            return result;
        },

        handlingUpdateUserOnClick: function () {
            $('.btn-update').on('click', function (event) {
                event.preventDefault();
                if ($('#frm').valid()) {
                    $('#updateUserConfirmDialog').modal('show');
                }
            });
        },

        setup: function () {
            this.validate();
        },

        run: function () {
            this.setup();
            this.handlingUpdateUserOnClick();
        }
    }

    $(document).ready(function () {
        UserDetail.run();
    });
})();