import { useState, useEffect } from 'react';

let listeners = [];
let state = { 
  pathname: "/"
 };

const setState = (newState) => {
  state = { ...state, ...newState };
  listeners.forEach((listener) => {
    listener(state);
  });
};

const usePathNameState = () => {
  const newListener = useState()[1];
  useEffect(() => {
    listeners.push(newListener);
  }, []);
  return [state, setState];
};

export default usePathNameState;