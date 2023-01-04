import React from 'react';
import { Outlet } from 'react-router-dom';
import { useRecoilState } from 'recoil';
import { authenticationState } from '../atoms/AuthenticationAtom';
import './ProtectedRoute.css';

const ProtectedRoute = () => {
    const [authenticated, setAuthenticated] = useRecoilState(authenticationState);

    if (!authenticated) {
        window.location.href = '/signin';
    }

    return (
        <div>
            <nav>
                <section id='nav-left'>
                    <a className='nav-btn' href='/tasks/new'>New Task</a>
                </section>
                <section id='nav-right'>
                    <a className='nav-btn' href='/logout'>Logout</a>
                </section>
            </nav>

            <Outlet/>
        </div>
    )
}

export default ProtectedRoute;