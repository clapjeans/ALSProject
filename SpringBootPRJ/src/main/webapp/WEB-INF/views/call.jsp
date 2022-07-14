<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>



</head>
<body>
<a id="create-kakaotalk-sharing-btn" href="javascript:;">
    <img
            src="https://developers.kakao.com/assets/img/about/logos/kakaotalksharing/kakaotalk_sharing_btn_medium.png"
            alt="카카오톡 공유 보내기 버튼"
    />
</a>
</body>
<script type='text/javascript'>

    var link = document.location.href;

    Kakao.init('7a41e2e2fe078d964dd06a6ada8cd641');

    Kakao.Link.createDefaultButton({
        container: '#create-kakaotalk-sharing-btn',
        objectType: 'feed',
        content: {
            title: '딸기 치즈 케익',
            description: '#케익 #딸기 #삼평동 #카페 #분위기 #소개팅',
            imageUrl:
                'http://k.kakaocdn.net/dn/Q2iNx/btqgeRgV54P/VLdBs9cvyn8BJXB3o7N8UK/kakaolink40_original.png',
            link: {
                mobileWebUrl: link,
                webUrl: link,
            },
        },

        buttons: [
            {
                title: '웹으로 보기',
                link: {
                    mobileWebUrl: link,
                    webUrl: link,
                },
            },

        ],
    })

</script>
</html>