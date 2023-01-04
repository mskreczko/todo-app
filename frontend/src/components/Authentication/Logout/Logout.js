import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useRecoilState } from 'recoil';
import { authenticationState } from '../../atoms/AuthenticationAtom';

export default function Logout() {
    const navigate = useNavigate();
    const [isAuthenticated, setAuthenticated] = useRecoilState(authenticationState);

    useEffect(() => {
        setAuthenticated(false);
        console.log('logged out');
        navigate('/');
    });
}