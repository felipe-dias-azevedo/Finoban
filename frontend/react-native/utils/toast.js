import Toast from 'react-native-toast-message';

export default function ToastUtils({ type, text1, text2 }) {
    console.log(type, text1, text2);
    return Toast.show({
        type: type,
        text1: text1,
        text2: text2,
        topOffset: 50,
        onPress: () => { Toast.hide() }
    });
}