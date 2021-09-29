import Loading from '../assets/images/Loading.gif';

export default function LoadingScreen() {
    return (
        <div>
            <div id="app-loading-modal">
                <img src={Loading} alt="loading" className="loading-modal" />
            </div>
        </div>
    );
}