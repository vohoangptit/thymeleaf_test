; (function () {
    'use strict';
    var element = $('#registTransmissionCompletedDialog');
    var RegistTransmissionCompletedDialog = {

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
        RegistTransmissionCompletedDialog.run();
    });
})();