; (function () {
    'use strict';

    var NoticeSearchList = {

        handlingNoticeListItemOnClick: function () {
            $(".table-row").click(function () {
                window.document.location = $(this).data("href");
            });
        },

        handlingClearButtonOnClick: function () {
            $('.btn-clear').on('click', function () {
                var searchConditions = $('#search-conditions');
                searchConditions.find('input[name=title]').val('').change();
                searchConditions.find('input[name=notice]').val('').change();
            });
        },

        run: function () {
            this.handlingNoticeListItemOnClick();
            this.handlingClearButtonOnClick();
        }
    }

    $(document).ready(function () {
        NoticeSearchList.run();
    });
})();