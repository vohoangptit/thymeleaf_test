; (function () {
    'use strict';
    var element = $('#deleteNoticeSuccessDialog');
    var NoticeConfirmDialog = {
        handlingModalOnClosing: function () {
            element.on('hidden.bs.modal', function () {
                //  window.location = '/notice/search';
                window.location = document.referrer;
            });
        },

        run: function () {
            this.handlingModalOnClosing();
        }
    }
    $(document).ready(function () {
        NoticeConfirmDialog.run();
    });
})();