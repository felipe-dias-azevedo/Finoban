import SimpleImageSlider from "react-simple-image-slider";
import Seguranca from '../assets/images/seguranca.png';
import Regulamentacao from '../assets/images/regulamentacao.png';
import Simplicidade from '../assets/images/simplicidade.png';
import Liberdade from '../assets/images/liberdade.png';

const images = [
  { url: Seguranca },
  { url: Regulamentacao },
  { url: Simplicidade },
  { url: Liberdade },
];

const Slider = () => {
  return (
    <div className="container-slider">
      <SimpleImageSlider
        width={'93%'}
        bgColor={'fffff'}
        height={500}
        images={images}
        showBullets={true}
        showNavs={true}
      />
    </div>
  );
}

export default Slider;