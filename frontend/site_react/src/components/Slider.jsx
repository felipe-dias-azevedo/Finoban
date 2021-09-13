import SimpleImageSlider from "react-simple-image-slider";
import Seguranca from '../assets/images/seguranca.png';
import Regulamentacao from '../assets/images/regulamentacao.png';
import Simplicidade from '../assets/images/simplicidade.png';
import Liberdade from '../assets/images/liberdade.png';
import Competitividade from '../assets/images/competitividade.png';

const images = [
  { url: Liberdade },
  { url: Simplicidade },
  { url: Competitividade },
  { url: Seguranca },
  { url: Regulamentacao },
];

const Slider = () => {
  return (
    <div className="container-slider">
      <SimpleImageSlider
        width={'93%'}
        bgColor={'fffff'}
        height={'90%'}
        images={images}
        showBullets={true}
        showNavs={true}
      />
    </div>
  );
}

export default Slider;