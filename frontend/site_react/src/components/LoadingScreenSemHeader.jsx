import Loading from '../assets/images/Loading.gif';
import Footer from './Footer';
import Header from './Header';

export default function LoadingScreen() {
    return (
        <>
            <div id="app-loading">
                <img src={Loading} alt="loading" className="loading" />
            </div>
        </>
    );
}