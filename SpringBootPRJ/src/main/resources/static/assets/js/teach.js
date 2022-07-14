// More API functions here:
// https://github.com/googlecreativelab/teachablemachine-community/tree/master/libraries/image

// the link to your model provided by Teachable Machine export panel
const URL = "https://teachablemachine.withgoogle.com/models/sj_5cUgio/";

let model, webcam, labelContainer, maxPredictions;

// Load the image model and setup the webcam
async function init() { //start 버튼을 누르면 init이라는 함수가 실행된다 init으로 모델을 실행
    const modelURL = URL + "model.json";
    const metadataURL = URL + "metadata.json";

    // load the model and metadata
    // Refer to tmImage.loadFromFiles() in the API to support files from a file picker
    // or files from your local hard drive
    // Note: the pose library adds "tmImage" object to your window (window.tmImage)
    model = await tmImage.load(modelURL, metadataURL); //모델이 저장된 파일을 가져와서 load하고 model에 저장해준다.
    maxPredictions = model.getTotalClasses();
    labelContainer = document.getElementById("label-container");
}


//지정된 정보에대해 찾을때 그 설명에 대해 찾을 수 있도록 나오게 하기
// run the webcam image through the image model
async function predict() {
    // predict can take in an image, video or canvas html element
    var image = document.getElementById("uploadimage") //id를 통해서 이미지를 가져옴
    const prediction = await model.predict(image,false);  //예측하기위한 이미지를 넣음 두번째인자는 flipped로 뒤집혔는지 안뒤집혔는지 확인
    if( prediction[0].className== "귤껍질" &&  prediction[0].probability.toFixed(2)==1.00){
        labelContainer.innerHTML = "<h5>분리배출 방법<a href='/infoPg?DICNM=귤껍질'>귤껍질</a>로 검색하기&nbsp;&nbsp;<a href='javascript:void(0)' onclick='removeUpload()'>다시찾기</a></h5>";
    }else if(prediction[1].className== "페트병" &&  prediction[1].probability.toFixed(2)==1.00){
        labelContainer.innerHTML =  "<h5>분리배출 방법<a href='/infoPg?DICNM=페트병'>페트병</a>로 검색하기&nbsp;&nbsp;<a href='javascript:void(0)' onclick='removeUpload()'>다시찾기</a></h5>";
    }else if(prediction[2].className=="우산" &&  prediction[2].probability.toFixed(2)==1.00){
        labelContainer.innerHTML =  "<h5>분리배출 방법<a href='/infoPg?DICNM=우산'>우산</a>으로 검색하기&nbsp;&nbsp;<a href='javascript:void(0)' onclick='removeUpload()'>다시찾기</a></h5>";
    }else{

        labelContainer.innerHTML="알 수가 없습니다. 사진을 다시 업로드 해주세요";
    }

    // for (let i = 0; i < maxPredictions; i++) {
    //     const classPrediction =
    //         prediction[i].className + ": " + prediction[i].probability.toFixed(2);
    //     labelContainer.childNodes[i].innerHTML = classPrediction;
    // }
}