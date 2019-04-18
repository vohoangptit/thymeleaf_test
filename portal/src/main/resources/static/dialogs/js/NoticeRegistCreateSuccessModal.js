; (function () {
    'use strict';
    var element = $('#registCreateSuccessDialog');
    var RegistCreateSuccessDialog = {

        handlingModalOnClosing: function () {
            element.on('hidden.bs.modal', function () {
                location.reload();
            });
        },

        run: function () {
            this.handlingModalOnClosing();
        }
    }
    $(document).ready(function () {
        RegistCreateSuccessDialog.run();
    });
})();