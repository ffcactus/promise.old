import React, { PropTypes, Component } from 'react';
import { connect } from 'react-redux';
import CSSModules from 'react-css-modules';
import styles from '../../styles/ServerFrame.css';
import ServerEthernetTable from './ServerEthernetTable';
import ProcessorSection from './ProcessorSection';
import Tab from '../common/Tab';

class ServerDetailFrame extends Component {
  constructor(props) {
    super(props);
  }

  // render() {
  //   const server = this.props.hardware.server;
  //   let content = null;
  //   if (server === null) {
  //     content = (<p>Empty</p>);
  //   } else {
  //     content = (
  //       <div>
  //         <section>
  //           <h1>Basic Information</h1>
  //           <p>Name: {server.Name}</p>
  //           <p>Description: {server.Description}</p>
  //           <p>State: {server.State}</p>
  //           <p>Health: {server.Health}</p>
  //           <p>PhysicalUUID: {server.PhysicalUUID}</p>
  //           <p>Address: {server.Address}</p>
  //           <p>Type: {server.Type}</p>
  //           <p>PowerState: {server.PowerState}</p>
  //           <p>BiosVersion: {server.BiosVersion}</p>
  //         </section>
  //         <section>
  //           <h1>Processors</h1>
  //           <table className="table">
  //             <thead className="thead">
  //               <tr className="tr">
  //                 <th className="th">Name</th>
  //                 <th className="th"> Model</th>
  //                 <th className="th">Architecture</th>
  //                 <th className="th">InstructionSet</th>
  //                 <th className="th">Socket</th>
  //                 <th className="th">MaxSpeedMHz</th>
  //                 <th className="th">TotalCores</th>
  //               </tr>
  //             </thead>
  //             <tbody className="tbody">
  //               {server.ComputerSystem.Processors.map(each => {
  //                 return (
  //                   <tr className="tr">
  //                     <td className="td">{each.Name}</td>
  //                     <td className="td">{each.Model}</td>
  //                     <td className="td">{each.Architecture}</td>
  //                     <td className="td">{each.InstructionSet}</td>
  //                     <td className="td">{each.Socket}</td>
  //                     <td className="td">{each.MaxSpeedMHz}</td>
  //                     <td className="td">{each.TotalCores}</td>
  //                   </tr>
  //                 );
  //               })}
  //             </tbody>
  //           </table>
  //         </section>
  //         <section>
  //           <h1>Memory</h1>
  //           <table className="table">
  //             <thead className="thead">
  //               <tr className="tr">
  //                 <th className="th">CapacityMiB</th>
  //                 <th className="th">MemoryDeviceType</th>
  //                 <th className="th">OperatingSpeedMhz</th>
  //                 <th className="th">DeviceLocator</th>
  //                 <th className="th">Socket</th>
  //                 <th className="th">Controller</th>
  //                 <th className="th">Channel</th>
  //                 <th className="th">Slot</th>
  //                 <th className="th">DeviceLocator</th>
  //               </tr>
  //             </thead>
  //             <tbody className="tbody">
  //               {server.ComputerSystem.Memory.map(each => {
  //                 return (
  //                   <tr className="tr">
  //                     <td className="td">{each.CapacityMiB}</td>
  //                     <td className="td">{each.MemoryDeviceType}</td>
  //                     <td className="td">{each.OperatingSpeedMhz}</td>
  //                     <td className="td">{each.DeviceLocator}</td>
  //                     <td className="td">{each.MemoryLocation.Socket}</td>
  //                     <td className="td">{each.MemoryLocation.Controller}</td>
  //                     <td className="td">{each.MemoryLocation.Channel}</td>
  //                     <td className="td">{each.MemoryLocation.Slot}</td>
  //                     <td className="td">{each.DeviceLocator}</td>
  //                   </tr>
  //                 );
  //               })}
  //             </tbody>
  //           </table>
  //         </section>
  //         <section>
  //           <h1>Ethernet Interface</h1>
  //           <ServerEthernetTable ethernetInterfaces={server.ComputerSystem.EthernetInterfaces}/>
  //         </section>
  //       </div>
  //     );
  //   }
  //   return (
  //     <div styleName="serverDetailFrame">
  //       {content}
  //     </div>
  //   );
  // }
  render() {
    const server = this.props.hardware.server;
    let content = null;
    if (server === null) {
      content = (<p>Empty</p>);
    } else {
      const pages = [
        {
          'title': 'System',
          'content': (<ProcessorSection/>)
        },
        {
          'title': 'Chassis',
          'content': (<ServerEthernetTable ethernetInterfaces={server.ComputerSystem.EthernetInterfaces}/>)
        }
      ];
      content = (<Tab pages={pages}/>);
    }
    return (
      <div styleName="serverDetailFrame">
        {content}
      </div>
    );
  }
}

function mapStateToProps(state) {
  const { hardware } = state;
  return { hardware };
}

ServerDetailFrame.propTypes = {
  hardware: PropTypes.object,
  children: PropTypes.object
};

export default connect(mapStateToProps)(CSSModules(ServerDetailFrame, styles));

