from keras import models
import keras
from flask import Flask, request
from PIL import Image
import numpy as np

app = Flask(__name__)

@app.route('/identify', methods=['POST'])
def identify():
    # 获取上传的文件
    file = request.files.get('file')

    if file:
        img = Image.open(file.stream)
        
        model = models.load_model("./my_model.keras")
        #model.summary()
        
        class_names = ['baihe', 'dangshen', 'gouqi', 'huaihua', 'jinyinhua']
        
        img = img.resize((128, 128))
        img_array = keras.preprocessing.image.img_to_array(img) / 255.0  # 归一化
        img_array = np.expand_dims(img_array, axis=0)  # 增加批量维度
        prediction = model.predict(img_array)
        class_indices = {i: name for i, name in enumerate(class_names)}
        predicted_class = class_indices[np.argmax(prediction)]
        confidence = np.max(prediction)
        
        print(predicted_class)
        
        # 返回预测结果
        return predicted_class
    else:
        return 'No file uploaded'

if __name__ == '__main__':
    app.run(debug=True, port=5000)  # Flask 默认端口是5000
