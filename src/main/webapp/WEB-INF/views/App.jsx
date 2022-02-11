import { useState, useEffect } from 'react';
import axios from 'axios';

export default function App() {
  const [formData, setFormData] = useState({
    nameA: '',
    nameB: '',
    yearA: '',
    yearB: '',
  });

  const { nameA, nameB, yearA, yearB } = formData;

  const OnChange = (e) =>
    setFormData({ ...formData, [e.target.name]: e.target.value });

  const handleSubmmit = (e) => {
    e.preventDefault();
    getTeamName();
  };

  const getTeamName = async () => {
    const res = await axios.get('http://localhost:9080/teamname');
    console.log(res.data);
  };

  const handleSubmmit2 = (e) => {
    e.preventDefault();
    createTeamName();
  };

  const createTeamName = async (e) => {
    const config = {
      headers: {
        'Content-type': 'application/json',
      },
    };
    const body = JSON.stringify({ nameA, nameB, yearA, yearB });
    const { data } = await axios.post(
      'http://localhost:9080/teamname',
      body,
      config
    );
    console.log('success');
  };

  return (
    <div className='App'>
      <div>
        <input
          type='text'
          placeholder='Enter the first two letter ...'
          name='nameA'
          value={nameA}
          onChange={(e) => OnChange(e)}
        />
        <input
          type='text'
          placeholder='Enter the first two letter ...'
          name='nameB'
          value={nameB}
          onChange={(e) => OnChange(e)}
        />
        <input
          type='text'
          name='yearA'
          placeholder='Enter the first two digits ...'
          value={yearA}
          onChange={(e) => OnChange(e)}
        />
        <input
          type='text'
          name='yearB'
          placeholder='Enter the first two digits ...'
          value={yearB}
          onChange={(e) => OnChange(e)}
        />
      </div>

      <div>
        <button type='button' onClick={handleSubmmit2}>
          Create Team Name
        </button>
      </div>
      <div>
        <button type='button' onClick={handleSubmmit}>
          Get Team Name
        </button>
      </div>
    </div>
  );
}
