;(function () {
    'use strict';
    var element = $('#updateUserSuccessDialog');
    var UserConfirmDialog = {
        handlingModalOnClosing: function () {
            element.on('hidden.bs.modal', function () {
              window.location=document.referrer;
                });
        },

        run: function () {
            this.handlingModalOnClosing();              
        }
    }
    $(document).ready(function () {
        UserConfirmDialog.run();
    });
})();